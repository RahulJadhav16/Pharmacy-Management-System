package com.pms.drug.inventory.Payload;

import org.springframework.http.HttpStatus;



public class ApiResponse {
    private String msg;
    private boolean success;
    private HttpStatus status;
    
    private ApiResponse() {
        // private constructor to enforce the use of the builder
    }
    
    public static ApiResponseBuilder builder() {
        return new ApiResponseBuilder();
    }
    
    public String getMsg() {
        return msg;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public HttpStatus getStatus() {
        return status;
    }
    
    public static class ApiResponseBuilder {
        private String msg;
        private boolean success;
        private HttpStatus status;
        
        public ApiResponseBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }
        
        public ApiResponseBuilder success(boolean success) {
            this.success = success;
            return this;
        }
        
        public ApiResponseBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }
        
        public ApiResponse build() {
            ApiResponse response = new ApiResponse();
            response.msg = this.msg;
            response.success = this.success;
            response.status = this.status;
            return response;
        }
    }
}

