package com.theniche.colivin.common.domain;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class GenericSpecification <T>{
    private final List<SearchCriteria> searchCriteriaList = new ArrayList<>();

    public void add(SearchCriteria searchCriteria){
        this.searchCriteriaList.add(searchCriteria);
    }

    public Specification<T> build(){
        return (root,query,builder)->{
            var predicates = new ArrayList<>();
            for(SearchCriteria searchCriteria : searchCriteriaList){
                var path = getPath(root,searchCriteria.getKey());
                switch (searchCriteria.getOperation()){
                    case EQUAL -> predicates.add(builder.equal(path,searchCriteria.getValue()));
                    case LIKE -> predicates.add(builder.like(path.as(String.class), "%" + searchCriteria.getValue() + "%"));
                    case GREATER_THAN -> predicates.add(builder.greaterThan(path.as(String.class), searchCriteria.getValue().toString()));
                    case LESS_THAN -> predicates.add(builder.lessThan(path.as(String.class), searchCriteria.getValue().toString()));
                }
            }
            return  builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Path<?> getPath(Root<T> root, String key){
        if(key.contains(".")){
            String [] parts = key
                    .split("\\.");
            Path<?> path = root.get(parts[0]);
            for(int i = 1; i < parts.length; i++){
                path = path.get(parts[i]);
            }
            return path;
        }
        return root.get(key);
    }
    public int getCriteriaCount() {
        return searchCriteriaList.size();
    }

}
