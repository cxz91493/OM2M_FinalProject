【OM2M Final Project】
資訊所碩一  張邑 尹崇珂


>>>>>>>>>>  包含檔案 <<<<<<<<<<

Server端：server.py
模擬Sensor：sensor.py
Node-RED：node_RED.txt
Android App：HomeSensor
說明文件 : README.txt
------------------------------------------------------------


>>>>>>>>>>  使用說明 <<<<<<<<<<

1. 開啟node-RED，並creare oM2M的4個components(前4個flow)
2. 更改server.py中GSCL_IP及NOTIFICATION_URL，啟動python server端
3. 設定sensor.py中url為node-RED所在位置，並啟動模擬sensor
4. 確定server可以正常接收資料
5. 開啟android app，即可取得sensor最新資料
------------------------------------------------------------