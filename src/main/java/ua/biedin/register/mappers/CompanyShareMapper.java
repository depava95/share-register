package ua.biedin.register.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.biedin.register.controller.response.PrivateShareResponse;
import ua.biedin.register.controller.response.PublicShareResponse;
import ua.biedin.register.entity.CompanyShare;

@Mapper
public interface CompanyShareMapper {

    CompanyShareMapper INSTANCE = Mappers.getMapper(CompanyShareMapper.class);

    PublicShareResponse toPublicResponse (CompanyShare companyShare);

    PrivateShareResponse toPrivateResponse(CompanyShare companyShare);

    CompanyShare toShareFromPublic (PublicShareResponse publicShareResponse);

    CompanyShare toShareFromPrivate (PrivateShareResponse privateShareResponse);

}
