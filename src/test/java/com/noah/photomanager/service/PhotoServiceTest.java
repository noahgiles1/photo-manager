package com.noah.photomanager.service;

import com.noah.photomanager.db.document.Photo;
import com.noah.photomanager.exceptions.PhotoNotFoundException;
import com.noah.photomanager.integration.MongoContainer;
import com.noah.photomanager.service.PhotoService;
import com.noah.photomanager.db.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Testcontainers
@SpringBootTest
class PhotoServiceTest extends MongoContainer {

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PhotoService photoService;
      
    @Test
    void whenAddPhoto_givenValidFile_thenShouldReturnId() throws IOException {
        String title = "Test Title";
        MultipartFile multipartFileMock = Mockito.mock(MultipartFile.class);

        // creating binary data for the Photo object
        byte[] binaryData = new byte[]{1, 2, 3};

        // Setting up response from the 'getBytes()' method of 'MultipartFile'
        when(multipartFileMock.getBytes()).thenReturn(binaryData);

        // Insert photo
        String photoId = photoService.addPhoto(title, multipartFileMock);

        // Get photo from repository
        Photo photo = photoRepository.findById(photoId).orElseThrow();

        // Verify
    	assertNotNull(photo);
    	assertEquals(title, photo.getTitle());
        assertArrayEquals(binaryData, photo.getImage().getData());
    }

    @Test
    void whenGetPhoto_givenValidId_thenShouldReturnPhoto() {
        String id = "123";
        Photo expectedPhoto = new Photo("testTitle");
        expectedPhoto.setId(id);
        photoRepository.insert(expectedPhoto);

        Photo actualPhoto = photoService.getPhoto(id);

        assertEquals(expectedPhoto.getId(), actualPhoto.getId());
        assertEquals(expectedPhoto.getTitle(), actualPhoto.getTitle());
    }

    @Test
    void whenGetPhoto_givenInvalidId_thenShouldThrowException() {
        String id = "InvalidId";
        assertThrows(PhotoNotFoundException.class, () -> {
            photoService.getPhoto(id);
        });
    }
}