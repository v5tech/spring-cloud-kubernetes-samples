package model

type WeatherInfo struct {
	Cityid     string `json:"cityid"`
	City       string `json:"city"`
	CityEn     string `json:"cityEn"`
	Country    string `json:"country"`
	CountryEn  string `json:"countryEn"`
	UpdateTime string `json:"update_time"`
	Data       []struct {
		Day         string   `json:"day"`
		Date        string   `json:"date"`
		Week        string   `json:"week"`
		Wea         string   `json:"wea"`
		WeaImg      string   `json:"wea_img"`
		WeaDay      string   `json:"wea_day"`
		WeaDayImg   string   `json:"wea_day_img"`
		WeaNight    string   `json:"wea_night"`
		WeaNightImg string   `json:"wea_night_img"`
		Tem         string   `json:"tem"`
		Tem1        string   `json:"tem1"`
		Tem2        string   `json:"tem2"`
		Humidity    string   `json:"humidity"`
		Visibility  string   `json:"visibility"`
		Pressure    string   `json:"pressure"`
		Win         []string `json:"win"`
		WinSpeed    string   `json:"win_speed"`
		WinMeter    string   `json:"win_meter"`
		Sunrise     string   `json:"sunrise"`
		Sunset      string   `json:"sunset"`
		Air         string   `json:"air"`
		AirLevel    string   `json:"air_level"`
		AirTips     string   `json:"air_tips"`
		Alarm       struct {
			AlarmType    string `json:"alarm_type"`
			AlarmLevel   string `json:"alarm_level"`
			AlarmContent string `json:"alarm_content"`
		} `json:"alarm"`
		Hours []struct {
			Hours    string `json:"hours"`
			Wea      string `json:"wea"`
			WeaImg   string `json:"wea_img"`
			Tem      string `json:"tem"`
			Win      string `json:"win"`
			WinSpeed string `json:"win_speed"`
		} `json:"hours"`
		Index []struct {
			Title string `json:"title"`
			Level string `json:"level"`
			Desc  string `json:"desc"`
		} `json:"index"`
	} `json:"data"`
	Aqi struct {
		UpdateTime string `json:"update_time"`
		Cityid     string `json:"cityid"`
		City       string `json:"city"`
		CityEn     string `json:"cityEn"`
		Country    string `json:"country"`
		CountryEn  string `json:"countryEn"`
		Air        string `json:"air"`
		AirLevel   string `json:"air_level"`
		AirTips    string `json:"air_tips"`
		Pm25       string `json:"pm25"`
		Pm25Desc   string `json:"pm25_desc"`
		Pm10       string `json:"pm10"`
		Pm10Desc   string `json:"pm10_desc"`
		O3         string `json:"o3"`
		O3Desc     string `json:"o3_desc"`
		No2        string `json:"no2"`
		No2Desc    string `json:"no2_desc"`
		So2        string `json:"so2"`
		So2Desc    string `json:"so2_desc"`
		Co         string `json:"co"`
		CoDesc     string `json:"co_desc"`
		Kouzhao    string `json:"kouzhao"`
		Yundong    string `json:"yundong"`
		Waichu     string `json:"waichu"`
		Kaichuang  string `json:"kaichuang"`
		Jinghuaqi  string `json:"jinghuaqi"`
	} `json:"aqi"`
}
