package com.iotiq.application.messages.image;

public record ImageResponse(
        String fileName
) {
    public static ImageResponse from(ImageDto imageDto) {
        if (imageDto == null) {return null;}
        return new ImageResponse(imageDto.fileName());
    }
}
