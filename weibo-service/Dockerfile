FROM python:3-alpine
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.sjtug.sjtu.edu.cn/g' /etc/apk/repositories \
    && apk update upgrade \
    && apk add --no-cache gcc g++ make tzdata curl wget build-base \
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone \
    && rm -rf /var/cache/apk/*
COPY app /app
COPY requirements.txt /app/requirements.txt
WORKDIR /app
RUN pip install --no-cache-dir --upgrade -i https://mirror.sjtu.edu.cn/pypi/web/simple -r /app/requirements.txt
EXPOSE 5000
CMD ["gunicorn", "--workers=4", "--bind=0.0.0.0:5000", "--log-level=debug", "main:app"]