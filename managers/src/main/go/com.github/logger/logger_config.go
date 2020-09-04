package logger

import (
	"../config"
	log "github.com/sirupsen/logrus"
)

type Logger struct {
	config *config.Config
}

func NewLogger(config *config.Config) *Logger {
	return &Logger{config: config}
}

func (logger *Logger) InitLogger() {
	switch logger.config.LoggerLvl {
	case "debug":
		log.SetLevel(log.DebugLevel)
		break
	case "info":
		log.SetLevel(log.InfoLevel)
		break
	case "error":
		log.SetLevel(log.ErrorLevel)
		break
	case "trace":
		log.SetLevel(log.TraceLevel)
		break
	default:
		log.Error("Logger level not found!")
		break
	}
}
