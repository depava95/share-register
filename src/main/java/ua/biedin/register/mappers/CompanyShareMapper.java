package ua.biedin.register.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.biedin.register.dto.PrivateShareResponse;
import ua.biedin.register.dto.PublicShareResponse;
import ua.biedin.register.entity.CompanyShare;

@Mapper
public interface CompanyShareMapper {

    CompanyShareMapper INSTANCE = Mappers.getMapper(CompanyShareMapper.class);

    PublicShareResponse toPublicResponse (CompanyShare companyShare);

    PrivateShareResponse toPrivateREsponse (CompanyShare companyShare);

    CompanyShare toShareFromPublic (PublicShareResponse publicShareResponse);

    CompanyShare toShareFromPrivate (PrivateShareResponse privateShareResponse);

}
