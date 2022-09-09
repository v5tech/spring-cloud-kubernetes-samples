import pandas as pd
import requests
import urllib3
import logging
from flask import Flask

urllib3.disable_warnings()

app = Flask(__name__)


@app.route("/")
def weibo_hot_news():
    headers = {
        'Connection': 'keep-alive',
        'Pragma': 'no-cache',
        'Cache-Control': 'no-cache',
        'Upgrade-Insecure-Requests': '1',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) '
                      'Chrome/96.0.4664.110 Safari/537.36 Edg/96.0.1054.62',
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,'
                  'image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
        'Accept-Language': 'zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7',
    }
    cookies = {
        'SUB': '_2A25MysX2DeRhGedO7loZ9CzFzDmIHXVvobA-rDV8PUNbmtB-LVqskW9NXU6ZHzPAAid8XPEIeIYICjMy1AvT2Pu4',
    }
    url = 'https://s.weibo.com/top/summary?cate=realtimehot'
    resp = requests.get(url, headers=headers, cookies=cookies)
    df = pd.read_html(resp.text, encoding='utf-8', skiprows=1)[0]  # 跳过第一行，查找页面中第一个表格
    df.columns = ['id', 'title', 'tag']  # 重命名列名
    df = df[df['id'] != '•']  # 过滤掉index不为'•'的行数据
    df = df.iloc[:, :-1]  # '取所有行数据，不包含最后一列 tag'
    df['news'] = df['title'].apply(lambda x: x.split('  ')[0])  # 拆分列
    df['hot'] = df['title'].apply(lambda x: x.split('  ')[1])  # 拆分列，并新增一列
    df.drop(axis=1, labels='title', inplace=True)  # 删除列
    return df.to_json(orient='records')

@app.route("/info")
def invoke_info():
    resp = requests.get('http://config-sample:8080/info')
    return resp.text

if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True, port=5000)

if __name__ != '__main__':
    # 如果不是直接运行，则将日志输出到 gunicorn 中
    gunicorn_logger = logging.getLogger('gunicorn.error')
    app.logger.handlers = gunicorn_logger.handlers
    app.logger.setLevel(gunicorn_logger.level)