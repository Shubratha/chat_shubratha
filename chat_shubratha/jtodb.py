#!/usr/bin/env python

try:
        import json as simplejson
except ImportError:
        import simplejson

#import twitstream
import MySQLdb

USER = ''
PASS = ''

USAGE = """%prog"""


conn = MySQLdb.connect(host = "localhost",
                       user = "root",
                       passwd = "",
                       db = "DemoData")

# Define a function/callable to be called on every status:
def callback(status):

    twitdb = conn.cursor ()
    twitdb.execute ("INSERT INTO user (firstName,lastName,dob,adharID,gender,email,username,password) VALUES (%s,%s,%s,%s,%s,%s,%s,%s)",(status.get('firstname'), status.get('lastname'), status.get('dob'), status.get('adharID', {}).get('gender'), status.get('email', {}).get('username'), status.get('password',{}),status))

   # print status
     #print "%s:\t%s\n" % (status.get('user', {}).get('screen_name'), status.get('text'))

if __name__ == '__main__':
    # Call a specific API method from the twitstream module:
    # stream = twitstream.spritzer(USER, PASS, callback)

    twitstream.parser.usage = USAGE
    (options, args) = twitstream.parser.parse_args()

    if len(args) < 1:
        args = ['Blackberry']

    stream = twitstream.track(USER, PASS, callback, args, options.debug, engine=options.engine)

    # Loop forever on the streaming call:
    stream.run()