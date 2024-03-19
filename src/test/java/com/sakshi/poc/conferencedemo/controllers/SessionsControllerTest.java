package com.sakshi.poc.conferencedemo.controllers;
import com.sakshi.poc.conferencedemo.ConferenceDemoApplication;
import com.sakshi.poc.conferencedemo.models.Session;
import com.sakshi.poc.conferencedemo.repositories.SessionRepository;
import com.sakshi.poc.conferencedemo.services.ISessionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.empty;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SessionsControllerTest {

    @MockBean
    private SessionRepository sessionRepository;

    @MockBean
    private ISessionService sessionService;
   // SessionsController sessionsController=new SessionsController(sessionService);

    @Autowired
    private MockMvc mockMvc;



//    @BeforeEach
//    void setUp() {
//        // Perform setup actions before each test method
//    }
//
//    @AfterEach
//    void tearDown() {
//        // Perform teardown actions after each test method.
//        clean up any resources or reset any state that was modified during the test methods.
//    }

    @Test
    @DisplayName("Get Sessions-Found")
    void getSessionsTest() throws Exception {
        List<Session> mockSessions=new ArrayList<Session>();
        Session mockSession=new Session();
        mockSession.setSessionId(1L);
        mockSession.setSessionName("Demo Session");
        mockSession.setSessionLength(6);
        mockSessions.add(mockSession);
        doReturn(mockSessions).when(sessionService).getSessions();
        mockMvc.perform(get("/api/v1/sessions"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void getSessionById() throws Exception {
        Session mockSession=new Session();
        mockSession.setSessionId(1L);
        mockSession.setSessionName("Demo Session");
        mockSession.setSessionLength(6);
        doReturn(mockSession).when(sessionService).getSessionById(1L);
        mockMvc.perform(get("/api/v1/sessions/{id}", 1L))
                .andExpect(status().isOk());//.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
   }

    @Test
    @DisplayName("Get Session by id-Not Found")
    void getSessionByIdTest() throws Exception {
        Session mockSession=new Session();
        mockSession.setSessionId(1L);
        mockSession.setSessionName("Demo Session");
        mockSession.setSessionLength(6);
        doReturn(null).when(sessionService).getSessionById(2L);
        mockMvc.perform(get("/api/v1/sessions/{id}", 2L))
                .andExpect(status().isNotFound());//.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createSession() {
    }

    @Test
    void deleteSession() {
    }

    @Test
    void updateSession() {
    }
}