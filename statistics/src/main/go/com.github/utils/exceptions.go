package utils

type Block struct {
	Try        func()
	Catch      func(Exception)
	Finally    func()
	Throw      func(err error)
	ThrowIfNil func(data interface{})
}

type Exception interface {
}

type NotFound struct {
	Message string
}

type BadRequest struct {
	Message string
}

func ThrowIfErr(err error, exception interface{}) {
	if err != nil {
		panic(exception)
	}
}

func ThrowIfNil(data interface{}, exception interface{}) {
	if data == nil || data == "" {
		panic(exception)
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
