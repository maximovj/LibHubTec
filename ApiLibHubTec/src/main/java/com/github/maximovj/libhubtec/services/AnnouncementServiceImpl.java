package com.github.maximovj.libhubtec.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.maximovj.libhubtec.dao.IAnnouncementDao;
import com.github.maximovj.libhubtec.model.Announcement;
import com.github.maximovj.libhubtec.response.AnnouncementResponse;
import com.github.maximovj.libhubtec.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements IAnnouncementServiceImpl {
    
    private final IAnnouncementDao announcementDao;
    private ApiResponse apiResponse;
    
    // Construir respuesta exitosa
    private ResponseEntity<AnnouncementResponse> buildSuccessResponse(String content, Optional<List<Announcement>> data)
    {
        AnnouncementResponse response = new AnnouncementResponse();
        this.apiResponse.setCtx_title("Anuncios");
        this.apiResponse.setCtx_content(content);
        this.apiResponse.setCode(HttpStatus.OK.value());
        this.apiResponse.setStatus("success");
        this.apiResponse.setSuccess(true);
        response.setApiResponse(apiResponse);
        response.setData(data);
        return ResponseEntity.ok(response);
    }
    
    @Override
    public ResponseEntity<AnnouncementResponse> listAnnouncements() {
        log.info("listAnnouncements : Iniciando");
        List<Announcement> list = new ArrayList<>();
        this.apiResponse = new ApiResponse();
        this.apiResponse.setUri("/v1/announcements");
        this.apiResponse.setType("GET");

        list = (List<Announcement>) this.announcementDao.findByIsPublishedTrueOrderByUpdatedAtDesc();
        
        log.info("listAnnouncements : Finalizado");
        return this.buildSuccessResponse("Listando anuncios correctamente", Optional.ofNullable(list));
    }

}
