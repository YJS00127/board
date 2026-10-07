let boardList = [];
let pageInfo = {};
let pageNumberButtons;

// 데이터 로딩 후 출력
async function loadData(){
    searchKeyword()
    const params = new URLSearchParams(location.search);

    const keyword = params.get('keyword') ?? '';
    const page = params.get('page') ?? 0;


    await fetch(`/api/board?keyword=${keyword}&page=${page}`)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            boardList = data.content;
            pageInfo = data;
            const pageGroup = Math.trunc(data.number/10)+1;
            console.log(pageGroup)
            boardListOutPut(data.pageable.offset)
            pagination(pageGroup, data.number, keyword, data.totalPages)
        })
}
// 1~10 -> 1  / 11~20 -> 2

// 게시글 검색
function searchKeyword() {
    document.getElementById("search-input").addEventListener('keyup',
        function(e){
        if(e.key === 'Enter'){
            const keyword = document.getElementById("search-input").value;
            if(keyword !== '' && keyword !== null) {
                location.href = `/board?keyword=${keyword}`;
            }
        }
    })
}

// 게시글 출력
function boardListOutPut(offset) {
    const tbody = document.querySelector("tbody");
    let boardNumber = offset;
    tbody.innerHTML = ``;
    boardList.forEach(boardDetailData => {
        const tr = document.createElement('tr');
        boardNumber += 1;
        tr.innerHTML =
            `<td class="boardNum">${boardNumber}</td>` +
            `<td class="boardTitle">
                <a href="/board/detail/${boardDetailData.id}">${boardDetailData.title}</a>
            </td>` +
            `<td class="boardWriter">${boardDetailData.writer}</td>` +
            `<td class="created-day">${boardDetailData.createdAt.slice(0,10)}</td>` +
            `<td class="created-time">${boardDetailData.createdAt.slice(11,16)}</td>`;
        document.getElementById("boardListOutPut").append(tr);
    })
}

// 페이지네이션 (페이지 생성, 클릭 시 이동 이벤트)
function pagination(pageGroup, page, keyword, totalPages) {

    // 페이지 생성
    const setPageButtons = (pageGroup, totalPages) => {
        const numberButtonWrapper = document.querySelector('.number-button-wrapper');
        numberButtonWrapper.innerHTML = '';

        const firstPage = (pageGroup - 1) * 10 + 1;
        const lastPage = Math.min(pageGroup * 10, totalPages);

        for (let i = firstPage; i <= lastPage; i++) {
            if(pageInfo.empty === true) return;
            numberButtonWrapper.innerHTML += `<span class="number-button">${i}</span>`;
        }
    }

    // 페이지 번호 클릭 이벤트
    const pageButtonsEvent = (keyword) => {
        pageNumberButtons = document.querySelectorAll('.number-button');

        pageNumberButtons.forEach((numberButton) => {
            numberButton.addEventListener('click', (e) => {
                if(keyword !== '' && keyword !== null) {
                    location.href = `/board?keyword=${keyword}&page=${numberButton.textContent - 1}`;
                } else{
                    location.href = `/board?page=${numberButton.textContent - 1}`;
                }
            })
        })
    }

    // 이전, 다음 페이지 이동
    const pageMoveButton = (keyword, page) => {
        const prev = document.querySelector('.prev-page-button');
        const next = document.querySelector('.next-page-button');

        prev.addEventListener('click', (e) => {
            if(!pageInfo.first){
                if(keyword !== '' && keyword !== null){
                    location.href = `/board?keyword=${keyword}&page=${page - 1}`;
                } else{
                    location.href = `/board?page=${page - 1}`;
                }
            }
        })

        next.addEventListener('click', (e) => {
            if(!pageInfo.last){
                if(keyword !== '' && keyword !== null) {
                    location.href = `/board?keyword=${keyword}&page=${page + 1}`;
                } else{
                    location.href = `/board?page=${page + 1}`;
                }
            }
        })
    }

    // 이전 페이지 그룹 (<) , 다음 페이지 그룹 (>) 이동
    const pageGroupMoveButton = (keyword, pageGroup) => {
        const prevGroup= document.querySelector('.prev-pageGroup-button');
        const nextGroup = document.querySelector('.next-pageGroup-button');

        prevGroup.addEventListener('click', (e) => {
            if(pageGroup !== 1){
                if(keyword !== '' && keyword !== null){
                    location.href = `/board?keyword=${keyword}&page=${(pageGroup-2)*10}`;
                } else{
                    location.href = `/board?page=${(pageGroup-2)*10}`;
                }
            }
        })

        nextGroup.addEventListener('click', (e) => {
            if(pageGroup !== Math.trunc(totalPages/10)+1){
                if(keyword !== '' && keyword !== null) {
                    location.href = `/board?keyword=${keyword}&page=${(pageGroup)*10}`;
                } else{
                    location.href = `/board?page=${(pageGroup)*10}`;
                }
            }
        })
    }

    setPageButtons(pageGroup, totalPages);
    pageButtonsEvent(keyword, page);
    pageMoveButton(keyword, page);
    pageGroupMoveButton(keyword, pageGroup);

    pageNumberButtons.forEach((numberButton) => {
        if (numberButton.classList.contains('selected')){
            numberButton.classList.remove('selected');
        }
    })

    pageNumberButtons[page%10].classList.add('selected');


}

loadData();