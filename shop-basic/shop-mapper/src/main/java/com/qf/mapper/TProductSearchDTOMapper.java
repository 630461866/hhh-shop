package com.qf.mapper;


import com.qf.entity.TProductSearchDTO;

import java.util.List;


public interface TProductSearchDTOMapper {

    List<TProductSearchDTO> selectAll();

    TProductSearchDTO selectById(Long id);
}
