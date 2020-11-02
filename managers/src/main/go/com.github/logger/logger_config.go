package logger

import (
	"github.com/cheshir/logrustash"
	log "github.com/sirupsen/logrus"
	"managers/src/main/go/com.github/config"
	"time"
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
	go addLogstashHook(logger.config)
}

func addLogstashHook(config *config.Config) {
	ticker := time.NewTicker(time.Duration(5*1000) * time.Millisecond)
	for {
		select {
		case <-ticker.C:
			if err := connectionLogstash(config); err != nil {
				log.WithFields(log.Fields{"Error": err}).
					Warn("Can't connect to logstash")
			} else {
				ticker.Stop()
				log.Debug("Logstash ticker is finished.")
			}
		}
	}
}

func connectionLogstash(config *config.Config) error {
	hook, err := logrustash.NewAsyncHook("tcp", config.LogstashUrl, config.ApplicationName)
	if err != nil {
		return err
	} else {
		hook.ReconnectBaseDelay = time.Second
		hook.ReconnectDelayMultiplier = 5
		hook.MaxReconnectRetries = 100
		log.AddHook(hook)
		return nil
	}
}
