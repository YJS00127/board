function pwChk(type){
    // 데이터 얻어오기
    while(true) {
        // 맞을 때, null일 때, (틀릴 때, 공백일 때) => 묶어서 다시 재시도
        // js에서 백데이터를 쓰려면 json으로 받아야하는듯..
        var pwInput = prompt('비밀번호를 입력해주세요.', '');
        if (pwInput === "") {
            alert("다시 시도해주세요.")
        } else if(pwInput === null) {
            break
        }
    }

}