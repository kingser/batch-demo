package com.yc.batch.batch;

import com.yc.batch.model.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * 描述:
 *
 * @author YC
 * @create 2018-10-06 16:37
 */
public class CsvItemProcessor  extends ValidatingItemProcessor<Person>{

    public  Person process (Person item) throws ValidationException{

        super.process(item);

        if (item.getNation().equals("汉族")){
            item.setNation("01");
        }else{
            item.setNation("02");
        }
        return item;
    }
}
