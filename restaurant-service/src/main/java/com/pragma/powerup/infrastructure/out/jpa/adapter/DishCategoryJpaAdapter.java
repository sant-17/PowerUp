package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.DishCategoryModel;
import com.pragma.powerup.domain.spi.IDishCategoryPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoCategoryFoundException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishCategoryEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishCategoryEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishCategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DishCategoryJpaAdapter implements IDishCategoryPersistencePort {

    private final IDishCategoryRepository categoryRepository;
    private final IDishCategoryEntityMapper categoryEntityMapper;


    @Override
    public DishCategoryModel saveDishCategory(DishCategoryModel categoryModel) {
        DishCategoryEntity categoryEntity = categoryRepository
                .save(categoryEntityMapper.toEntity(categoryModel));
        return categoryEntityMapper.toDishCategoryModel(categoryEntity);
    }

    @Override
    public List<DishCategoryModel> getAllDishCategories() {
        List<DishCategoryEntity> categoryEntityList = categoryRepository.findAll();
        if(categoryEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toDishCategoryList(categoryEntityList);
    }

    @Override
    public DishCategoryModel getDishCategoryById(Long id) {
        return categoryEntityMapper.toDishCategoryModel(categoryRepository.findById(id).
                orElseThrow(NoCategoryFoundException::new));
    }
}
