package config

import (
	"../utils"
	"github.com/tkanos/gonfig"
	"log"
	"os"
)

const (
	ProfileDev     = "dev"
	ProfileProd    = "prod"
	ProfileTest    = "test"
	ProfileDefault = "default"
)

type Config struct {
	IsLoggerFile    bool
	ServerPort      string
	DatabaseConfig  DatabaseConfig
	EurekaConfig    EurekaConfig
	LoggerLvl       string
	IsSwaggerEnable bool
}

type DatabaseConfig struct {
	DatabaseHost     string
	DatabasePort     int
	DatabaseUser     string
	DatabasePassword string
	DatabaseName     string
	DdlAuto          string
	MaxIdleConns     int
	MaxOpenConns     int
}

type EurekaConfig struct {
	EurekaUrl      string
	InstanceId     string
	HostName       string
	SecurePort     int
	App            string
	IPAddr         string
	VipAddress     string
	HomePageUrl    string
	StatusPageUrl  string
	HealthCheckUrl string
	EnableEureka   bool
}

func NewConfig() *Config {
	config := Config{}
	var err interface{}
	profile := os.Getenv("ENVIRONMENT")
	profile = utils.Ternary(profile == "", ProfileDefault, profile).(string)
	switch profile {
	case ProfileDev:
		err = gonfig.GetConf("src/main/resources/config.development.json", &config)
	case ProfileProd:
		err = gonfig.GetConf("src/main/resources/config.production.json", &config)
	case ProfileTest:
		err = gonfig.GetConf("src/main/resources/config.production.json", &config)
	case ProfileDefault:
		err = gonfig.GetConf("src/main/resources/config.default.json", &config)
	}
	log.Println("Profile name:", profile, "Application port:", config.ServerPort)
	if err != nil {
		panic(err)
	}
	return &config
}
