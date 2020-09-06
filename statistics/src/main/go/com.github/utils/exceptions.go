package utils

import "../models"

type Block struct {
	Try        func()
	Catch      func(Exception)
	Finally    func()
	Throw      func(err error)
	ThrowIfNil func(data interface{})
}

type Exception interface {
}

func ThrowIfErr(err error, code int, message string) {
	if err != nil {
		panic(models.Error{Code: code, Message: message})
	}
}

func ThrowIfNil(data interface{}, code int, message string) {
	if data == nil || data == "" {
		panic(models.Error{Code: code, Message: message})
	}
}

func (tcf Block) Do() {
	if tcf.Finally != nil {
		defer tcf.Finally()
	}
	if tcf.Catch != nil {
		defer func() {
			if r := recover(); r != nil {
				tcf.Catch(r)
			}
		}()
	}
	tcf.Try()
}
