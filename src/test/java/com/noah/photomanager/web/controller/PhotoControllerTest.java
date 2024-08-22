package com.noah.photomanager.web.controller;

import com.noah.photomanager.db.document.Photo;
import com.noah.photomanager.integration.MongoContainer;
import com.noah.photomanager.service.PhotoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PhotoControllerTest extends MongoContainer {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PhotoService photoService;

    @Test
    void shouldAddPhoto() throws Exception {
        String title = "Test";
        byte[] bytes = "Hello, World!".getBytes();
        MockMultipartFile mockFile =
                new MockMultipartFile("image", bytes);

        String id = photoService.addPhoto(title, mockFile);
        //when(photoService.addPhoto("Test", file)).thenReturn("Photo added successfully!");

        mockMvc.perform(multipart("/api/photos/add")
                        .file("image", bytes)
                        .param("title", "Test"))
                .andExpect(status().isOk());

        Photo photo = photoService.getPhoto(id);
        assertArrayEquals(bytes, photo.getImage().getData());
        assertEquals(title, photo.getTitle());
    }
}