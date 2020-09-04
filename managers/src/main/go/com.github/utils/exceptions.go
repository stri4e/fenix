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

func ThrowIfErr(err error, message string) {
	if err != nil {
		panic(message)
	}
}

func ThrowIfNil(data interface{}, message string) {
	if data == nil || data == "" {
		panic(message)
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
