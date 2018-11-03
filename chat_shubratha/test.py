import csv
import MySQLdb
import sys
from xml.etree import ElementTree
import json
def get_connection(user, db, password=""):
   connection = MySQLdb.connect(
       host='localhost',
       user='root',
       password='',
       db='DemoData')
   return connection
def read_csv(file_path):
   lines = list(csv.reader(open(file_path)))
   return lines
def read_json(file_path):
   print("inside read_json")
   lines = []
   with open(file_path,"r") as f:
       text = f.read()
       print(text)
       json_data = json.loads(text)
       for d in json_data:
           x = d["user"]
           #lines = [x[val] for val in d[""]]
           row=[x["firstname"],
               x["lastname"],
               x["dob"],
               x["adharID"],
               x["gender"],
               x["email"],
               x["username"],
               x["password"]]
           lines.append(row)
   return lines
def read_xml(file_path):
   lines = []
   dom = ElementTree.parse(file_path).getroot()
   for line in dom:
       lines.append(
           [line[0].text,
           line[1].text,
           line[2].text,
           line[3].text,
           line[4].text,
           line[5].text,
           ine[6].text,
           line[7].text])
   return lines
def parse_report_file(file_path):
   with open(file_path) as unknown_file:
      c = unknown_file.read(1)
      print(c)
   if c == "[":
       return read_json(file_path)
   elif c == " < ":
       return read_xml(file_path)
   else:
       return read_csv(file_path)
def main_2(file_path):
   print (file_path)
   records = parse_report_file(file_path)
   conn = get_connection(user='root', db='DemoData')
   insert_records(conn, records)
   #close_connection(conn)
   print("done")
def insert_records(conn, records):
   print("hello :inside records")
   cur = conn.cursor()
   for i in records:
     print(i)
     cur.execute('INSERT INTO user (firstName, lastName , dob , adharID , gender , email , username , password) VALUES (%s,%s,%s,%s,%s,%s,%s,%s)',i)
   conn.commit()
   cur.close()

if __name__ == '__main__':
  print ("hello")
  if(len(sys.argv) < 2):
      print("too few arguments")
      sys.exit(1)   
  file_path = sys.argv[1]
  main_2(file_path)