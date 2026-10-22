// 게시글 등록
function insertContents() {
    const formData = new FormData();

    const title = document.getElementById('title').value
    const writer = document.getElementById('writer').value
    const content = document.getElementById('content').value
    const pw = document.getElementById('pw').value
    const pwConfirm = document.getElementById('pwConfirm').value
    const files = document.getElementById('file').files

    formData.append("title", title);
    formData.append("writer", writer);
    formData.append("content", content);
    formData.append("pw", pw);
    formData.append("pwConfirm", pwConfirm);

    for(const file of files){
        formData.append("files", file);
    }
    console.log(files)

    // fetch(`/api/board`, {
    //     method: "POST",
    //     body: formData
    // })
    //     .then(async response => {
    //         console.log(response)
    //         const result = await response.json()
    //         console.log(result)
    //         if(!response.ok){
    //             showErrorMsg(result)
    //             return
    //         }
    //         return result;
    //     })
    //     .then(result => {
    //         console.log(result)
    //         if(result>0) {
    //             alert('게시물을 등록하였습니다.')
    //             location.href='/board'
    //         }
    //     })
}

// 에러 메세지 출력
function showErrorMsg(errorMsg) {
    const title = document.getElementById("title");
    const writer = document.getElementById("writer");
    const content = document.getElementById("content");
    const pw = document.getElementById("pw");
    const pwConfirm = document.getElementById("pwConfirm");

    title.classList.remove("border-red");
    writer.classList.remove("border-red");
    content.classList.remove("border-red");
    pw.classList.remove("border-red");
    pwConfirm.classList.remove("border-red");

    if(errorMsg["title"]){
        title.placeholder = errorMsg["title"];
        title.classList.add("border-red");
    }
    if(errorMsg["writer"]){
        writer.placeholder = errorMsg["writer"];
        writer.classList.add("border-red");
    }
    if(errorMsg["content"]){
        content.placeholder = errorMsg["content"];
        content.classList.add("border-red");
    }
    if(errorMsg["pw"]){
        pw.placeholder = errorMsg["pw"];
        pw.classList.add("border-red");
    }
    if(errorMsg["pwEquals"]){
        pwConfirm.placeholder = errorMsg["pwEquals"];
        pwConfirm.classList.add("border-red");
    }
}


// 초기화 버튼
function resetValue() {
    document.querySelector(".insert-board").reset();
    document.querySelectorAll(".insert-board input, .insert-board textarea").forEach((element) => {
        element.classList.remove("border-red");
    });
    document.querySelector(".file-list").innerHTML = '';
}


// 등록일 설정
function setDate() {
    const today = new Date();

    const year = today.getFullYear();
    const month = today.getMonth() + 1;
    const date = today.getDate();

    document.getElementById("createdAt").innerHTML = year + "/" + month + "/" + date;
}


// 첨부된 파일 보기
function showFiles() {
    const fileInput = document.getElementById("file");
    const fileList = document.querySelector(".file-list");

    fileInput.addEventListener('change', () => {
        fileList.innerHTML = '';

        for(const file of fileInput.files) {
            fileList.innerHTML += `
            <p class="file-name">${file.name}
            <button type='button' class="file-remove" onclick=removeFile(this)></button>
            </p>`;
        }
    })
}

function removeFile(element) {
    const dataTransfer = new DataTransfer();

    const files = document.getElementById('file').files
    const fileName = element.parentElement.textContent.trim();

    Array.from(files)
        .filter(file => file["name"] !== fileName)
        .forEach(file => {
            dataTransfer.items.add(file);
        });

    document.getElementById("file").files = dataTransfer.files;

    element.parentElement.remove();
}

setDate()
showFiles()