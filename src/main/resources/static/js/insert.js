function insertContents(){
    const formData = new FormData();

    const title = document.getElementById('title').value
    const writer = document.getElementById('writer').value
    const content = document.getElementById('content').value
    const pw = document.getElementById('pw').value
    const pwConfirm = document.getElementById('pwConfirm').value
    const files = document.getElementById("file").files

    formData.append("title", title);
    formData.append("writer", writer);
    formData.append("content", content);
    formData.append("pw", pw);
    formData.append("pwConfirm", pwConfirm);

    for(const file of files){
        formData.append("files", file);
    }

    fetch(`/api/board`, {
        method: "POST",
        body: formData
    })
        .then(async response => {
            const result = await response.json()
            if(!response.ok){
                showErrorMsg(result)
                return
            }
            return result;
        })
        .then(result => {
            if(result>0) {
                alert('게시물을 등록하였습니다.')
                // location.href='/board'
            }
        })
}

function showErrorMsg(errorMsg){
    const title = document.getElementById("title");
    const writer = document.getElementById("writer");
    const content = document.getElementById("content");
    const pw = document.getElementById("pw");
    const pwConfirm = document.getElementById("pwConfirm");

    // title.innerHTML = ``;
    // writer.innerHTML = ``;
    // content.innerHTML = ``;
    // pw.innerHTML = ``;
    // pwConfirm.innerHTML = ``;

    if(errorMsg["title"]){
        title.placeholde = errorMsg["title"];
    }
    if(errorMsg["writer"]){
        writer.placeholde = errorMsg["writer"];
    }
    if(errorMsg["content"]){
        content.placeholde = errorMsg["content"];
    }
    if(errorMsg["pw"]){
        pw.placeholde = errorMsg["pw"];
    }
    if(errorMsg["pwEquals"]){
        pwConfirm.placeholde = errorMsg["pwEquals"];
    }
}

function resetValue() {
    document.querySelector(".write-content").reset();

    document.querySelectorAll(".write-content .error").forEach(span => {
        span.innerHTML = '';
    })
}