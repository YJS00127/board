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
        .then(response => response.json())
        .then(result => {
            console.log(result)
            if(result>0){
                alert('게시물을 등록하였습니다.')
                location.href='/board'
            } else{
                alert('게시물 등록에 실패하였습니다.')
            }
        })
}