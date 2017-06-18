import requests
import xml.etree.ElementTree as ET
from flask import Flask, request
import base64
import warnings
import json

# Initialize the Flask application
app = Flask(__name__)

APP_NAME = 'SENSOR'
DATA_NAME = 'DATA'

NSCL_IP = 'localhost'
NSCL_URL = ''

GSCL_IP = '140.116.82.100'
GSCL_URL = 'http://{}:8080/om2m/gscl/applications/{}/containers/{}/contentInstances'.format(GSCL_IP, APP_NAME, DATA_NAME)

NOTIFICATION_URL = 'http://140.116.82.100:5000/monitor'

sensorData = dict()

#subscirbe my application to gscl
def subscribe():
    url = GSCL_URL + '/subscriptions'
    xml = "<om2m:subscription xmlns:om2m=\"http://uri.etsi.org/m2m\"><om2m:contact>{}</om2m:contact></om2m:subscription>".format(NOTIFICATION_URL)
    headers = {'Content-Type': 'application/xml'}
    response = requests.post(url, data=xml, headers=headers, auth=('admin', 'admin')).text
    root = ET.fromstring(response)
    if 'subscription' in root.tag:
        print('[Subscribe] Subscribed')
    else:
        print('[Subscribe] Error:', root.find('{http://uri.etsi.org/m2m}statusCode').text)

@app.route('/monitor', methods=['POST'])

def monitor():
    global sensorData
    data = request.data
    root = ET.fromstring(data)
    status = root.find('{http://uri.etsi.org/m2m}statusCode')
    if status.text == 'STATUS_CREATED':
        print('[Monitor] Status Created')
        
        # Get Representation
        rawContent = root.find('{http://uri.etsi.org/m2m}representation').text
        content = base64.b64decode(rawContent).decode("utf-8")
        root = ET.fromstring(content)
        
        # Get Content
        rawContent = root.find('{http://uri.etsi.org/m2m}content').text
        content = base64.b64decode(rawContent).decode("utf-8")
        root = ET.fromstring(content)
        
        # Get String
        strs = root.findall('str')
        for str in strs:
            sensorData[str.attrib['name']] = str.attrib['val']
        sensorData = json.loads(sensorData['data'])
        print(sensorData)
    else:
        print('[Monitor]', status.text)
    return ''

@app.route('/check', methods=['GET'])
def getData():
    print('[Get Data] Check Data')
    return str(sensorData['temperature'])+ ','+ str(sensorData['humidity'])

if __name__ == '__main__':
    warnings.simplefilter("ignore")
    subscribe()
    app.run(host='0.0.0.0', threaded=True)