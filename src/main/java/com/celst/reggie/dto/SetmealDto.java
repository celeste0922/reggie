package com.celst.reggie.dto;

import com.celst.reggie.pojo.Setmeal;
import com.celst.reggie.pojo.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
