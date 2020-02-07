from flask import Flask
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)


class NetTest(Resource):
    def get(self, name):
        return {"net_test": name}

api.add_resource(NetTest, '/net_test/<string:name>')

app.run(host='0.0.0.0', port=8000)
