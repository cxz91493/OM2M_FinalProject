【OM2M Final Project】
資訊所碩一  張邑 尹崇珂


>>>>>>>>>>  包含檔案 <<<<<<<<<<

Server端：server.py
模擬Sensor：sensor.py
Node-RED：node_RED.txt
Android App：HomeSensor
說明文件 : README.txt
報告投影片：第三組期末報告.pptx
使用操作：使用操作.pdf
------------------------------------------------------------


>>>>>>>>>>  使用說明 <<<<<<<<<<

1. 開啟gscl
2. 開啟node-RED，並creare oM2M的4個components(前4個flow)
3. 更改server.py中GSCL_IP及NOTIFICATION_URL，啟動python server端
4. 設定sensor.py中url為node-RED所在位置，並啟動模擬sensor
5. 確定node-RED可以接收sensor的資料
6. 確定server可以正常接收資料
7. 開啟android app，即可取得sensor最新資料，記得修改server所在的IP(GetThermometer.java及GetHumidity.java中的url)
------------------------------------------------------------