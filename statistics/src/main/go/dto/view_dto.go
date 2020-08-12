package dto

import (
	"time"
)

// ViewDto swagger:model
type ViewDto struct {
	Id        uint          `json:"id" example:"1"`
	AccountId int64         `json:"accountId,omitempty" example:"1"`
	CreatedAt time.Time     `json:"createAt" example:"2019-11-09T21:21:46+00:00"`
	Products  []*ProductDto `json:"products"`
}
