// 수정 전 게시글 데이터
function boardContentOutput(id) {

    const title = document.getElementById("title")
    const writer = document.getElementById('writer')
    const content = document.getElementById('content')

    fetch(`/api/board/detail/${id}`)
        .then(response => response.json())
        .then(boardDetail => {
            title.value = boardDetail.title
            writer.value = boardDetail.writer
            content.value = boardDetail.content
        })
}

// 게시글 데이터 수정
const updateContent = () => {
    const title = document.getElementById("title").value
    const writer = document.getElementById('writer').value
    const content = document.getElementById('content').value

    fetch(`/api/board/` + id, {
        method: "PUT",
        body: JSON.stringify({
            title,
            writer,
            content
        }),
        headers: {
            'Content-Type' : 'application/json'
        }
    })
        .then(response => response.json())
        .then(result => {
            if(result>0){
                alert('게시글을 수정하였습니다.')
                location.href=`/board/detail/`+id;
            } else{
                alert('게시글 수정에 실패하였습니다.')
            }
        })
}

const updateCancel = () => {
    location.href=`/board/detail/` + id
}

boardContentOutput(id)