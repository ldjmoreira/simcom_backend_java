package com.nelioalves.cursomc.resources.utils;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.nelioalves.cursomc.dto.ComponenteDTO2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListComponenteDTOconverter {
	
    public static List<ComponenteDTO2> convertToObj(Map<String, Map<String, Object>> productMap) {
        List<ComponenteDTO2> objList = new ArrayList<>();

        for (Map.Entry<String, Map<String, Object>> entry : productMap.entrySet()) {
            try {
                Map<String, Object> productData = entry.getValue();
                Integer id = Integer.parseInt(productData.get("id").toString());
                Integer tipoComponenteId = Integer.parseInt(productData.get("tipoComponenteId").toString());
                Integer userId = Integer.parseInt(productData.get("userId").toString());
                Integer quantidade = Integer.parseInt(productData.get("quantidade").toString());

                ComponenteDTO2 obj = new ComponenteDTO2();
                
                obj.setId(id);
                obj.setTipoComponenteId(tipoComponenteId);
                obj.setUserId(userId);
                obj.setQuantidade(quantidade);
                
                objList.add(obj);
            } catch (NumberFormatException | NullPointerException e) {
                // Handle invalid product data here.
                // For example, you could log the error or skip the product.
            }
        }

        return objList;
    }
}
