package umc.spring.apiPayload.code;

public interface BaseErrorCode {

    public ErrorResponseDTO getReason();

    public ErrorResponseDTO getReasonHttpStatus();
}
