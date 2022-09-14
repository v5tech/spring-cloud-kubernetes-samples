package main

import (
	"encoding/json"
	"net/http"
	"weather-service/model"

	"github.com/gin-gonic/gin"
	"github.com/go-resty/resty/v2"
)

func GetWeather(ctx *gin.Context) {
	client := resty.New()
	resp, _ := client.R().Get("http://www.tianqiapi.com/api?version=v9&appid=23035354&appsecret=8YvlPNrz")
	weather := model.WeatherInfo{}
	_ = json.Unmarshal(resp.Body(), &weather)
	ctx.JSON(http.StatusOK, weather)
}

func main() {
	router := gin.Default()
	router.GET("/", GetWeather)
	_ = router.Run(":8080")
}
