package sample.cafekiosk.spring.api.service.mail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

@ExtendWith(MockitoExtension.class)
class MailServiceTestWithBDD {

  @Mock
  private MailSendClient mailSendClient;

  @Mock
  private MailSendHistoryRepository mailSendHistoryRepository;

  @InjectMocks
  private MailService mailService;

  @DisplayName("메일 전송 테스트")
  @Test
  void sendMail() {
    //given
//    final MailSendClient mailSendClient = mock(MailSendClient.class);
//    final MailSendHistoryRepository mailSendHistoryRepository = mock(
//        MailSendHistoryRepository.class);

//    MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

//    Mockito.when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
//        .thenReturn(true);

//    doReturn(true)
//        .when(mailSendClient)
//        .sendEmail(anyString(), anyString(), anyString(), anyString());

//    Mockito.when(
//            mailSendHistoryRepository.save(any(MailSendHistory.class)))
//        .thenReturn();

    BDDMockito.given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
        .willReturn(true);

    //when
    final boolean result = mailService.sendMail("", "", "", "");

    //then
    assertThat(result).isTrue();

    verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
  }

}