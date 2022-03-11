package io.quinio.transaction.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import io.quinio.transaction.bean.ResponseBean;
import io.quinio.transaction.dao.IMuVentaDAO;
import io.quinio.transaction.exception.QuinioException;
import io.quinio.transaction.openEnum.CodeErrorEnum;
import io.quinio.transaction.utils.Constants;

/**
 * @author Luis Angel Rodriguez Fitta
 * Implementacion de la interfaz de IMuVentaDAO
 */
@Repository
public class MuVentaDAO implements IMuVentaDAO {
	/**
	 * Logger
	 */
	private final static Logger LOGGER = LogManager.getLogger(MuVentaDAO.class);
	/**
	 * Host sandbox
	 */
	@Value("${sandbox.muventa.host}")
	private String host;
	
	/**
	 * Endpoint servicio para obtener las transacciones
	 */
	@Value("${sandbox.muventa.transactions.endpoint}")
	private String endPointTransaction;

	/**
	 * Header X-Escale-Details
	 */
	@Value("${sandbox.muventa.header.escale}")
	private String escaleDetails;
	
	/**
	 * Metodo para consumir el endPoint orders/v1/loyalty/bulk/export/transactions
	 * @param page NUmero de la pagina
	 * @param size Numero de registros por pagina
	 */
	@Override
	public ResponseBean getTransactions(Integer page, Integer size) throws QuinioException {
		LOGGER.info("Page: " + page);
		LOGGER.info("Size: " + size);
		ResponseBean response = null;
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(Constants.HEADER_ESCALE_DETAILS, escaleDetails);
		
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		
		String tmp = String.format(endPointTransaction, page, size);
		String url = host + tmp;
		ResponseEntity<ResponseBean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ResponseBean.class);
		if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			response = responseEntity.getBody();
		} else {
			throw new QuinioException("Ocurrio un error al consumir el servicio " + tmp, CodeErrorEnum.SANDBOX);
		}
		return response;
	}

}
