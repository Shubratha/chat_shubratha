import pymysql
import os
import json

# read JSON file which is in the next parent folder
file = os.path.abspath('/home/student/chat_shubratha') + "/jsonfile.json"
#json_data=open(file).read()
#print (json_data)
#json_obj = json.load(json_data)
connection = pymysql.connect(
       host='localhost',
       user='root',
       password='',
       db='DemoData')

listdata=[]
def readdata(file_):
    with open(file_,"r") as f:
        json_data = f.read()
        data= json.loads(json_data)
        for i in data:
            line=i["user"]
            row=[line["firstname"],
               line["lastname"],
               line["dob"],
               line["adharID"],
               line["gender"],
               line["email"],
               line["username"],
               line["password"]]
            listdata.append(line)
    return listdata

cur=connection.cursor()
readdata(file)
for i in listdata:
     print(i)
     cur.execute('INSERT INTO user (firstName, lastName , dob , adharID , gender , email , username , password) VALUES (%s,%s,%s,%s,%s,%s,%s,%s)',i)
connection.commit()
cur.close()