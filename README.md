# Geolocation Tracker

## 🎯 About <a name="about"></a>
An example of a REST service whose task is to <strong>receive</strong> and <strong>save/update geolocation information</strong>  from mobile devices. </br></br>
Follow the technologies and solutions present in this project below:
- <strong>Spring Boot 2</strong>
- <strong>Maven</strong> 
- Databases using <strong>Postgres on Docker</strong>
- <strong>Spring Data JPA</strong>
- Validation of a user input
- <strong>Unit Testing</strong>

## :white_check_mark: API testing <a name="about"></a> 

- Testing the _GeolocationRepository_ class using the settings with `@DataJpaTest`

```java
@Test
    void selectExistsDeviceId_deviceIdExists_returnTrue() {
        //given
        String deviceId = "12345";
        Geolocation geolocation = new Geolocation(deviceId, 505430, 1423412);
        underTest.save(geolocation);

        //when
        boolean expected = underTest.selectExistsDeviceId(deviceId);

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void selectExistsDeviceId_deviceIdNotExist_returnFalse() {
        //given
        String deviceId = "12345";

        //when
        boolean expected = underTest.selectExistsDeviceId(deviceId);

        //then
        assertThat(expected).isFalse();
    }
  ```
  
- _GeolocationService_ class test examples using `@Mock` and `@Captor`.

```java
 @Test
    void addGeolocation_deviceIdNotExist_geolocationSaved() {
        //given
        Geolocation geolocation = new Geolocation("12345", 505430, 1423412);

        //when
        underTest.addGeolocation(geolocation);

        //then
        then(geolocationRepository).should().save(geolocationArgumentCaptor.capture());
        Geolocation geolocationArgumentCaptorValue = geolocationArgumentCaptor.getValue();
        assertThat(geolocationArgumentCaptorValue).isEqualTo(geolocation);
    }
   ```
   ```java
    @Test
    void getAllGeolocations_emptyList_thrownNotFoundException() {
        //given
        given(geolocationRepository.findAll()).willReturn(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> underTest.getAllGeolocations())
                .isInstanceOf(NotFoundException.class)
                .hasMessage("The geolocation list is empty");
    }
   ```
   
   
