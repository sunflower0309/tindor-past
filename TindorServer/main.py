import os
import json
import socket
from flask import Flask, escape, request, send_from_directory

app = Flask(__name__)

'''
import pymongo

cluster = pymongo.MongoClient(
    "mongodb://admin:R4jLNSbjcfuOG4mM@SG-Tindor-30704.servers.mongodirector.com:27017/admin"
)


db = cluster["local"]
collection = db["test"]
collection.insert_one({"hello": "world"})
'''

port = 8000
users = {}


def populate_users():
    ip = socket.gethostbyname(socket.gethostname())
    for filename in os.listdir("./user_photo"):
        users[filename[:-4]] = { "age": "18", "photo_url": "http://{}:{}/user_photo/{}".format(ip, port, filename)}


@app.route('/')
def hello():
    name = request.args.get("name", "World")
    return f'Hello, {escape(name)}!'


@app.route('/test')
def test():
    return "Server connected successfully!"


@app.route('/all_users')
def all_users():
    r = json.dumps(users)
    return r


@app.route('/user_photo/<path:path>')
def user_photo(path):
    return send_from_directory('user_photo', path)


if __name__ == "__main__":
    populate_users()
    app.run(host='0.0.0.0', port=port)
