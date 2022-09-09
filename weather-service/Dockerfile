FROM golang:1.18-alpine as builder
WORKDIR /app
COPY . .
RUN go env -w GO111MODULE=on \
    && go env -w GOPROXY=https://goproxy.cn,direct \
    && go env -w CGO_ENABLED=0 \
    && go env \
    && go mod tidy \
    && go build -o weather .

FROM scratch
LABEL MAINTAINER="fengj@anchnet.com"
WORKDIR /app
COPY --from=builder /app/weather .
EXPOSE 8080
ENTRYPOINT ["./weather"]