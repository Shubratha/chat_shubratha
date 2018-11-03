import json
#import conn
import pymysql
con = pymysql.connect(host = 'localhost',user = 'root',passwd = '',db = 'DemoData')
cursor = con.cursor()

#cursor = conn.provide_cursor();

json_file = open("info.json")
json_data = json.loads(json_file.read())
data_value = json_data["data"]
for each_info in data_value:
	cursor.execute("insert into users_info values(%s,%s,%s,%s,%s,%s,%s,%s)" % (each_info["first_name"],each_info["last_name"],each_info["date_of_birth"],each_info["aadhar_id"],each_info["gender"],each_info["email"],each_info["username"],each_info["password"]))

# now to fetch the data
cursor.execute(conn.sql_select)	
results = cursor.fetchall();
for row in results:
		print ("first name is=%s" % (row[0],))	
		
con.close();