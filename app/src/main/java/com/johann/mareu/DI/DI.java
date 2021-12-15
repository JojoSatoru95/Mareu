package com.johann.mareu.DI;


import com.johann.mareu.Service.DummyMeetingApiService;
import com.johann.mareu.Service.MeetingApiService;

public class DI {

        private static final MeetingApiService service = new DummyMeetingApiService();

        /**
         * Get an instance on @{@link MeetingApiService}
         * @return
         */
        public static MeetingApiService getMeetingApiService() {
            return service;
        }

        /**
         * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
         * @return
         */
        public static MeetingApiService getNewInstanceApiService() {
            return new DummyMeetingApiService();
        }
    }

