package com.domain.notification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {

    OBJECT_UPDATE {
        @Override
        public String message() {
            return "사용자 %s가 프로젝트 %s를 수정했습니다.";
        }
    },
    INSTRUMENT_CREATE {
        @Override
        public String message() {
            return "사용자 %s가 프로젝트 %s에 악기를 추가했습니다.";
        }
    },
    INSTRUMENT_DELETE {
        @Override
        public String message() {
            return "사용자 %s가 프로젝트 %s에 악기를 삭제했습니다.";
        }
    },
    PROJECT_INVITE {
        @Override
        public String message() {
            return "사용자 %s가 당신을 프로젝트 %s에 초대했습니다.";
        }
    },
    PROJECT_EXCLUDE {
        @Override
        public String message() {
            return "사용자 %s가 당신을 프로젝트 %s에 추방했습니다.";
        }
    },
    PROJECT_DELETE {
        @Override
        public String message() {
            return "사용자 %s가 당신의 프로젝트 %s를 삭제했습니다.";
        }
    };

    public abstract String message();

}
