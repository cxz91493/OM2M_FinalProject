import random
import json
import requests
import asyncio

async def func():
	while True:
		temp = random.randint(18,25)
		wet = random.randint(40,60)
		print("temperature: ", temp, "℃")
		print("humidity: ", wet, "%")
		data = {
			'temperature' : temp,
			'humidity' : wet
		}
		url = 'http://140.116.82.100:1880/sensor'

		requests.post(url, data=json.dumps(data))
		print('sent')
		await asyncio.sleep(10)

loop = asyncio.get_event_loop()
# Blocking call which returns when the display_date() coroutine is done
loop.run_until_complete(func())
print('close')
loop.close()

