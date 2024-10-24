package com.domain.external.mail.enums;

/**
 * 메일 발송 타입 상수
 */
public enum TemplateType {

    EMAIL_VALIDATION {
        @Override
        public String fileName() {
            return "email-validation.html";
        }
    },
    RESET_PASSWORD {
        @Override
        public String fileName() {
            return "reset-password.html";
        }
    },
    PROJECT_INVITE {
        @Override
        public String fileName() {
            return "project-invite.html";
        }
    },
    PROJECT_OUT {
        @Override
        public String fileName() {
            return "project-out.html";
        }
    };

    public abstract String fileName();
}
