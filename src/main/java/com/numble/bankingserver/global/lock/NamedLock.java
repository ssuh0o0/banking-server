package com.numble.bankingserver.global.lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

/**
 * JdbcTemplate 을 이용하여 USER LEVEL LOCK 을 사용하는 클래스
 */
@Slf4j
@RequiredArgsConstructor
public class NamedLock implements LockManager{

    private static final String GET_LOCK = "SELECT GET_LOCK(:userLockName, :timeoutSeconds)";
    private static final String RELEASE_LOCK = "SELECT RELEASE_LOCK(:userLockName)";
    private static final String EXCEPTION_MESSAGE = "LOCK 을 수행하는 중에 오류가 발생하였습니다.";

    private static final int TIMEOUT_SECONDS = 1;
    private static final ResultSetExtractor<Integer> RESULT_SET_EXTRACTOR = rs -> {
        if (rs.next()) {
            return rs.getInt(1);
        }
        return null;
    };

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void executeWithLock(String lockName, Runnable runnable) {
        try {
            getLock(lockName);
            runnable.run();
        } finally {
            releaseLock(lockName);
        }
    }

    /**
     * NamedParameterJdbcTemplate 사용
     */
    private void getLock(String lockName) {

        Map<String, Object> params = new HashMap<>();
        params.put("userLockName", lockName);
        params.put("timeoutSeconds", TIMEOUT_SECONDS);
        log.info("GetLock!! userLockName : [{}], timeoutSeconds : [{}]", lockName, TIMEOUT_SECONDS);
        Integer result = namedParameterJdbcTemplate.queryForObject(GET_LOCK, params, Integer.class);
        checkResult(result, lockName, "GetLock");
    }

    /**
     * NamedParameterJdbcTemplate 사용
     */
    private void releaseLock(String lockName) {

        Map<String, Object> params = new HashMap<>();
        params.put("userLockName", lockName);

        log.info("ReleaseLock!! userLockName : [{}]", lockName);
        Integer result = namedParameterJdbcTemplate.queryForObject(RELEASE_LOCK, params, Integer.class);
        checkResult(result, lockName, "ReleaseLock");
    }

    private void checkResult(Integer result, String lockName, String type) {
        if (result == null) {
            log.error("USER LEVEL LOCK 쿼리 결과 값이 없습니다. type = [{}], userLockName : [{}]", type, lockName);
            throw new RuntimeException(EXCEPTION_MESSAGE);
        }
        if (result != 1) {
            log.error("USER LEVEL LOCK 쿼리 결과 값이 1이 아닙니다. type = [{}], result : [{}] userLockName : [{}]", type, result, lockName);
            throw new RuntimeException(EXCEPTION_MESSAGE);
        }
    }

}
