package repository

import (
	"github.com/jinzhu/gorm"
	"statistics/src/main/go/com.github/entity"
)

type PopularProductRepo struct {
	db *gorm.DB
}

func NewPopularProductRepo(db *gorm.DB) *PopularProductRepo {
	return &PopularProductRepo{db: db}
}

func (repo *PopularProductRepo) FirstOrCreate(popular *entity.PopularProduct) (*entity.PopularProduct, error) {
	tx := repo.db.Begin()
	err := tx.Where("product_id = ?", popular.ProductId).
		FirstOrCreate(&popular).Error
	if err != nil {
		tx.Begin()
	}
	tx.Commit()
	return popular, err
}

func (repo *PopularProductRepo) FindByIds(productIds []uint) ([]*entity.PopularProduct, error) {
	var products []*entity.PopularProduct
	err := repo.db.Find(&products, "users.id IN ?", productIds).Error
	if err != nil {
		return nil, err
	}
	return products, err
}

func (repo *PopularProductRepo) TotalSumView() (int64, error) {
	var result int64
	row := repo.db.Table("popular_product").
		Select("sum(view_count)").Row()
	err := row.Scan(&result)
	if err != nil {
		return 0, err
	}
	return result, err
}

func (repo *PopularProductRepo) TotalSumBought() (int64, error) {
	var result int64
	row := repo.db.Table("popular_product").
		Select("sum(bought_count)").Row()
	err := row.Scan(&result)
	if err != nil {
		return 0, err
	}
	return result, err
}

func (repo *PopularProductRepo) UpdatePercentView(viewCount int, percentView float32, productId uint) error {
	tx := repo.db.Begin()
	err := tx.Table("popular_product").
		Where("product_id = ?", productId).
		Update("view_count", viewCount, "percent_view", percentView).Error
	if err != nil {
		tx.Begin()
	}
	tx.Commit()
	return err
}

func (repo *PopularProductRepo) UpdatePercentBought(boughtCount int, percentBought float32, productId uint) error {
	tx := repo.db.Begin()
	err := tx.Table("popular_product").
		Where("product_id = ?", productId).
		Update("bought_count", boughtCount, "percent_bought", percentBought).Error
	if err != nil {
		tx.Begin()
	}
	tx.Commit()
	return err
}
