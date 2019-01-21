package pl.socodeit.legacy.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.AdditionalAnswers;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LegacyServiceTest {

    private LegacyService legacyService;

    @Mock
    private ExternalService externalService;
    @Mock
    private AnotherService anotherService;
    @Mock
    private NextService nextService;

    @Test
    void should_throw_npe_when_external_service_is_null (){
        //Given
        legacyService = new LegacyService(null, null, null);

        //When
        Throwable thrown = catchThrowable(() -> legacyService.veryImportantLegacyLogic(null, null, false));

        //Then
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }


    @Test
    void should_throw_npe_when_integer_param_is_null(){
        //Given
        legacyService = new LegacyService(externalService, null, null);

        //When
        Throwable thrown = catchThrowable(() -> legacyService.veryImportantLegacyLogic(null, null, false));

        //Then
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @Test
    void should_throw_npe_when_another_service_is_null(){
        //Given
        legacyService = new LegacyService(externalService, null, null);

        //When
        Throwable thrown = catchThrowable(() -> legacyService.veryImportantLegacyLogic(null, 1, false));

        //Then
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @Test
    void should_throw_npe_when_next_service_is_null(){
        //Given
        legacyService = new LegacyService(externalService, anotherService, null);

        //When
        Throwable thrown = catchThrowable(() -> legacyService.veryImportantLegacyLogic(null, 1, false));

        //Then
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @Test
    void should_pass_method_with_duplicates(){
        //Given
        legacyService = new LegacyService(externalService, anotherService, nextService);

        //When
        String result = legacyService.veryImportantLegacyLogic(null, 1, false);

        //Then
        assertThat(result).isEqualTo("Duplicates exist for id null");
    }

    @Test
    void should_pass_method_with_no_duplicates(){
        //Given
        legacyService = new LegacyService(externalService, anotherService, nextService);
        when(nextService.removeDuplicates(anyList())).then(AdditionalAnswers.returnsFirstArg());

        //When
        String result = legacyService.veryImportantLegacyLogic(null, 1, false);

        //Then
        assertThat(result).isEqualTo("No dupliactes found for id null");
    }

    @Test
    void should_call_external_service_once_when_flag_is_false(){
        //Given
        legacyService = new LegacyService(externalService, anotherService, nextService);

        //When
        String result = legacyService.veryImportantLegacyLogic(null, 1, false);

        //Then
        verify(externalService).callRemote();
    }

    @Test
    void should_not_call_external_service_when_flag_is_true(){
        //Given
        legacyService = new LegacyService(externalService, anotherService, nextService);

        //When
        String result = legacyService.veryImportantLegacyLogic(null, 1, true);

        //Then
        verify(externalService,never()).callRemote();
    }

    @ParameterizedTest
    @ValueSource(ints={0,1,3})
    void should_call_another_service_exactly_times_like_int_param(Integer param){
        //Given
        legacyService = new LegacyService(externalService, anotherService, nextService);

        //When
        String result = legacyService.veryImportantLegacyLogic("a", param, true);

        //Then
        verify(anotherService,times(param)).getFromDb(anyString(),anyInt());
    }

    @Test
    void should_call_next_service_once(){
        //Given
        legacyService = new LegacyService(externalService, anotherService, nextService);

        //When
        String result = legacyService.veryImportantLegacyLogic("a", 1, true);

        //Then
        verify(nextService).removeDuplicates(anyList());
    }

}