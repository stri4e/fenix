package logger

import (
	"../config"
	"log"
	"os"
	"strings"
)

var (
	levels     string
	InfoLvl    *log.Logger
	WarningLvl *log.Logger
	ErrorLvl   *log.Logger
	DebugLvl   *log.Logger
)

type Logger struct {
	config *config.Config
}

func NewLogger(config *config.Config) *Logger {
	return &Logger{config: config}
}

func (logger *Logger) InitLogger() {
	if logger.config.IsLoggerFile {
		file, err := os.OpenFile("logs.txt", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0666)
		if err != nil {
			panic(err.Error())
		}
		InfoLvl = log.New(file, "INFO: ", log.Ldate|log.Ltime|log.Lshortfile)
		WarningLvl = log.New(file, "WARN: ", log.Ldate|log.Ltime|log.Lshortfile)
		ErrorLvl = log.New(file, "ERROR: ", log.Ldate|log.Ltime|log.Lshortfile)
		DebugLvl = log.New(file, "DEBUG: ", log.Ldate|log.Ltime|log.Lshortfile)
	} else {
		InfoLvl = log.New(os.Stdout, "INFO: ", log.Ldate|log.Ltime|log.Lshortfile)
		WarningLvl = log.New(os.Stdout, "WARN: ", log.Ldate|log.Ltime|log.Lshortfile)
		ErrorLvl = log.New(os.Stdout, "ERROR: ", log.Ldate|log.Ltime|log.Lshortfile)
		DebugLvl = log.New(os.Stdout, "DEBUG: ", log.Ldate|log.Ltime|log.Lshortfile)
	}
	levels = logger.config.LoggerLvl
}

func Debug(values ...interface{}) {
	if strings.Contains(levels, "debug") {
		DebugLvl.Println(values)
	}
}

func Warning(values ...interface{}) {
	if strings.Contains(levels, "warning") {
		WarningLvl.Println(values)
	}
}

func Info(values ...interface{}) {
	if strings.Contains(levels, "info") {
		InfoLvl.Println(values)
	}
}

func Error(values ...interface{}) {
	if strings.Contains(levels, "error") {
		InfoLvl.Println(values)
	}
}
