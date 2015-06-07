import json
import urllib2
import urllib
import cookielib

cookiejar = cookielib.CookieJar()
urlopener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cookiejar))


req2=urllib2.Request("http://127.0.0.1:8080/courseCircleList?course_id=1")
#req2.add_header("Cookie", "3467CFBD11FF70169C6EB1EF3EEDEC1C")
#r2=urllib2.urlopen(req2)
r2=urlopener.open(req2)

print(r2.getcode())
print(r2.info())
print(r2.read())

print("second request")

#data = urllib.urlencode({'username':'duanlei', 'password':'duanlei123', '_csrf':'2464a89c-5c41-44d8-b29d-893310d9d120'})
data = urllib.urlencode({'username':'duanlei', 'password':'duanlei123'})

req1=urllib2.Request("http://127.0.0.1:8080/login", data, { 'Accept':'text/html', 'Content-Type': 'application/x-www-form-urlencoded'})

r1=urlopener.open(req1)
print(r1.read())


