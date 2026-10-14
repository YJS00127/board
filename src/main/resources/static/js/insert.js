function insertContents(){
    const title = document.getElementById('title').value
    const writer = document.getElementById('writer').value
    const content = document.getElementById('content').value
    const pw = document.getElementById('pw').value
    const pwConfirm = document.getElementById('pwConfirm').value

    console.log(pw)
    console.log(pwConfirm)


    fetch(`/api/board`, {
        method: "POST",
        body: JSON.stringify({
            title,
            writer,
            content,
            pw,
            pwConfirm
        }),
        headers: {'Content-Type': 'application/json'}
    })
        .then(async response => {
            const result = await response.json()
            console.log(result)
            if(!response.ok){
                showErrorMsg(result)
                return
            }
            return result;
        })
        .then(result => {
            if(result>0) {
                alert('게시물을 등록하였습니다.')
                location.href='/board'
            }
        })
}

function showErrorMsg(errorMsg){
    const title = document.getElementById("error-title");
    const writer = document.getElementById("error-writer");
    const content = document.getElementById("error-content");
    const pw = document.getElementById("error-pw");
    const pwConfirm = document.getElementById("error-pwConfirm");

    title.innerHTML = ``;
    writer.innerHTML = ``;
    content.innerHTML = ``;
    pw.innerHTML = ``;
    pwConfirm.innerHTML = ``;

    if(errorMsg["title"]){
        title.textContent = errorMsg["title"];
    }
    if(errorMsg["writer"]){
        writer.textContent = errorMsg["writer"];
    }
    if(errorMsg["content"]){
        content.textContent = errorMsg["content"];
    }
    if(errorMsg["pw"]){
        pw.textContent = errorMsg["pw"];
    }
    if(errorMsg["pwEquals"]){
        pwConfirm.textContent = errorMsg["pwEquals"];
    }
}

function resetValue() {
    document.querySelector(".write-content").reset();

    document.querySelectorAll(".write-content .error").forEach(span => {
        span.innerHTML = '';
    })

}