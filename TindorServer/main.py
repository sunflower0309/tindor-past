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
users_json_path = "./users.json"


def populate_users():
    ip = socket.gethostbyname(socket.gethostname())
    print("*** IP ***:", ip)
    for filename in os.listdir("./user_photo"):
        users[filename[:-4]] = {
            "age": "18",
            "password": "123",
            "photo_url": "http://{}:{}/user_photo/{}".format(ip, port, filename)}


def populate_users_with_json():
    global users
    if os.path.exists(users_json_path):
        with open(users_json_path) as f:
            users = json.load(f)
    else:
        ip = socket.gethostbyname(socket.gethostname())
        for filename in os.listdir("./user_photo"):
            users[filename[:-4]] = {
                "age": "18",
                "password": "123",
                "photo_url": "http://{}:{}/user_photo/{}".format(ip, port, filename)}
        j = json.dumps(users, indent=4)
        with open(users_json_path, 'w') as f:
            f.write(j)


@app.route('/')
def hello():
    return "Hello world!"


@app.route('/test')
def test():
    return "Server connected successfully!"


@app.route('/all_users')
def all_users():
    r = json.dumps(users)
    return r


@app.route('/user_photo/<path>')
def user_photo(path):
    return send_from_directory('user_photo', path)


@app.route('/cat_photo/<path>')
def cat_photo(path):
    return send_from_directory('cat_photo', path)


@app.route('/login/<username>/<password>')
def login(username, password):
    if username in users and users[username]["password"] == password:
        return True
    return False


if __name__ == "__main__":
    populate_users()
    app.run(host='0.0.0.0', port=port)
