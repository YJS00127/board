function insertContents(){
    const title = document.getElementById('title').value
    const writer = document.getElementById('writer').value
    const content = document.getElementById('content').value
    const pw = document.getElementById('pw').value

    fetch(`/api/board`, {
        method: "POST",
        body: JSON.stringify({
            title,
            writer,
            content,
            pw
        }),
        headers: {'Content-Type': 'application/json'}
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
            console.log(result)
            if(result>0){
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

    title.innerHTML = ``;
    writer.innerHTML = ``;
    content.innerHTML = ``;
    pw.innerHTML = ``;

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
}